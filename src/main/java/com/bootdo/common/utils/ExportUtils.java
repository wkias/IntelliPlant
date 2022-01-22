package com.bootdo.common.utils;

import com.bootdo.common.annotation.Log;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ExportUtils<T> {


    public void exportFile(HttpServletResponse response, HttpServletRequest request, String filename, List obj) throws Exception {
        if(obj.size()==0){return;}
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        HttpSession session = request.getSession();
        List<T> list = (List<T>) obj;
        // 设置要导出的文件的名字
        String fileName = filename + UUID.randomUUID() + ".xls";
        int rowNum = 1;
        //父类表头
        Field[] parentFields=null;
        // headers表示excel表中第一行的表头 在excel表中添加表头
        Field[] childFields = list.get(0).getClass().getDeclaredFields();//获得属性
        Field[] fields;
        if(list.get(0).getClass().getSuperclass()!=null){
            parentFields=list.get(0).getClass().getSuperclass().getDeclaredFields();
            fields=new Field[parentFields.length+childFields.length];
            System.arraycopy(parentFields,0,fields,0,parentFields.length);
            System.arraycopy(childFields,0,fields,parentFields.length,childFields.length);
        }else{
            fields=childFields;
        }
        //过滤掉没有加注解的变量
        List<Field> fieldList= Arrays.asList(fields);
        fieldList=new ArrayList<>(fieldList);//转化为List方便进行变量过滤
        ListIterator<Field> iterator = fieldList.listIterator();
        while(iterator.hasNext()){
            Field field=iterator.next();
            if(field.getAnnotation(Log.class)==null){
                iterator.remove();
            }
        }
        fields=fieldList.toArray(new Field[fieldList.size()]);
        //
        HSSFRow row = sheet.createRow(0);
        int cellIndex=0;//列索引
        for (int i = 0; i < fields.length; i++) {
            System.out.println("field:"+fields[i]);
            System.out.println("annotation:"+fields[i].getAnnotation(Log.class));
                HSSFCell cell = row.createCell(cellIndex);
                HSSFRichTextString text = new HSSFRichTextString(fields[i].getAnnotation(Log.class).value());
                cell.setCellValue(text);
                cellIndex++;

        }

        //在表中存放查询到的数据放入对应的列
        for (T item : list) {
            HSSFRow row1 = sheet.createRow(rowNum);
            Class clazz = item.getClass();

            int i = 0;//列索引
            for (Field field : fields) {
                try{
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object content = getMethod.invoke(item, null);
                //System.out.println("fieldName:"+field.getName()+"content:"+content);
                    //日期格式转换
                if(content!=null&&content instanceof Date){
                    content=DateUtils.format((Date) content,DateUtils.DATE_TIME_PATTERN);
                }

                row1.createCell(i).setCellValue(content == null ? null : content.toString());
                }catch (IntrospectionException e){
                    System.err.println("属性"+field.getName()+"没有合适的get方法，该列所有值将被设为空！");
                    continue;
                }finally {
                    i++;
                }

            }
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}

