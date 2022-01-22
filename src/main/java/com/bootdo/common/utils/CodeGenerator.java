package com.bootdo.common.utils;

import com.bootdo.common.service.CodeNumberService;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CodeGenerator {
    @Autowired
    CodeNumberService codeNumberService;


    private PropertiesConfiguration getConfig(){
        try {
            return new PropertiesConfiguration("codeSettings.properties");
        } catch (ConfigurationException e) {
            throw new BDException("获取配置文件失败，", e);
        }
    }
    public String getCodeByType(String codeType){
        //获取生成编号的模板
        String str=getConfig().getString(codeType);

        int numberInt=codeNumberService.getCodeNumberByCodeType(codeType);
        List<String> list=new ArrayList<String>();
        Pattern p = Pattern.compile("\\{([^}]*)\\}");
        Matcher m = p.matcher(str);
        while(m.find()){
            list.add(m.group());
        }
        for(String s:list){
            //转义{}
            s=s.replace("{","\\{");
            s=s.replace("}","\\}");
            if(s.contains("X")){
                int length=s.substring(2,s.length()-2).length();
                int number=numberInt;
                String numberStr=String.format("%0"+length+"d",number);
                str=str.replaceAll(s,numberStr);
            }else{
                SimpleDateFormat sdf=new SimpleDateFormat(s.substring(2,s.length()-2));
                String DateS=sdf.format(new Date());
                str=str.replaceAll(s,DateS);
            }
        }
        codeNumberService.increaseCode(codeType);
        return str;
    }
}
