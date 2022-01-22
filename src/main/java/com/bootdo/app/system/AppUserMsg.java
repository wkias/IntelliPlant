package com.bootdo.app.system;

import com.bootdo.common.config.Constant;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.oa.controller.NotifyController;
import com.bootdo.oa.domain.NotifyDO;
import com.bootdo.oa.domain.NotifyRecordDO;
import com.bootdo.oa.service.NotifyRecordService;
import com.bootdo.oa.service.NotifyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
@ResponseBody
public class AppUserMsg {
    @Autowired
    NotifyService notifyService;
    @Autowired
    NotifyRecordService notifyRecordService;

    @GetMapping("app/userMsg/list")
    PageUtils selfList(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        query.put("userId", ShiroUtils.getUserId());
        return notifyService.selfList(query);
    }

    @GetMapping("app/readMsg/{id}")
    NotifyDO read(@PathVariable("id") Long id, Model model) {
        NotifyDO notify = notifyService.get(id);
        //更改阅读状态
        NotifyRecordDO notifyRecordDO = new NotifyRecordDO();
        notifyRecordDO.setNotifyId(id);
        notifyRecordDO.setUserId(ShiroUtils.getUserId());
        notifyRecordDO.setReadDate(new Date());
        notifyRecordDO.setIsRead(Constant.OA_NOTIFY_READ_YES);
        notifyRecordService.changeRead(notifyRecordDO);
        model.addAttribute("notify", notify);
        return notify;
    }

}
