package com.bootdo.factory.reimburse.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.factory.reimburse.domain.ConsumeDO;
import com.bootdo.factory.reimburse.domain.LoanDO;
import com.bootdo.factory.reimburse.domain.ReimbursementDO;
import com.bootdo.factory.reimburse.service.ReimbursementService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author willice
 * @email willicen@qq.com
 * @date 2020-05-25 22:39:14
 */

@Controller
@RequestMapping("/reimburse/reimbursement")
public class ReimbursementController {
    @Autowired
    private ReimbursementService reimbursementService;

    @GetMapping()
    @RequiresPermissions("reimburse:reimbursement:reimbursement")
    String Reimbursement() {
        return "factory/reimburse/reimbursement/reimbursement";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("reimburse:reimbursement:reimbursement")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<ReimbursementDO> reimbursementList = reimbursementService.list(query);
        int total = reimbursementService.count(query);
        return new PageUtils(reimbursementList, total);
    }

    @GetMapping("/add")
    @RequiresPermissions("reimburse:reimbursement:add")
    String add(Model model) {
        model.addAttribute("id", UUID.randomUUID().toString());
        return "factory/reimburse/reimbursement/add";
    }

    @GetMapping("/account")
    @RequiresPermissions("reimburse:reimbursement:add")
    String account() {
        return "factory/reimburse/reimbursement/bankAccount";
    }

    @GetMapping("/loan")
    @RequiresPermissions("reimburse:reimbursement:add")
    String loan() {
        return "factory/reimburse/reimbursement/loan";
    }

    @GetMapping("/edit/{reimburseId}")
    @RequiresPermissions("reimburse:reimbursement:edit")
    String edit(@PathVariable("reimburseId") String reimburseId, Model model) {
        ReimbursementDO reimbursement = reimbursementService.get(reimburseId);
        if (reimbursement.getLoan() == null) {
            List<LoanDO> loan = new LinkedList<>();
            loan.add(new LoanDO());
            reimbursement.setLoan(loan);
        }
        if (reimbursement.getConsume() == null) {
            List<ConsumeDO> consume = new LinkedList<>();
            consume.add(new ConsumeDO());
            reimbursement.setConsume(consume);
        }
        model.addAttribute("reimbursement", reimbursement);
        return "factory/reimburse/reimbursement/edit";
    }

    @GetMapping("/detail/{reimburseId}")
    @RequiresPermissions("reimburse:reimbursement:detail")
    String detail(@PathVariable("reimburseId") String reimburseId, Model model) {
        ReimbursementDO reimbursement = reimbursementService.get(reimburseId);
        model.addAttribute("reimbursement", reimbursement);
        return "factory/reimburse/reimbursement/detail";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("reimburse:reimbursement:add")
    public R save(ReimbursementDO reimbursement, HttpServletRequest request) throws Exception {
        reimbursement.setCreatUser(ShiroUtils.getUserId().intValue());
        reimbursement.setCreateTime(new Timestamp(new Date().getTime()));
        reimbursement.setIsDeleted(false);
        String[] consumeIds = request.getParameterValues("consumeId");
        String[] consumeTypes = request.getParameterValues("consumeType");
        String[] payers = request.getParameterValues("payer");
        String[] time = request.getParameterValues("time");
        String[] amounts = request.getParameterValues("consumeAmount");
        String[] invoiceNums = request.getParameterValues("invoiceNumber");
        String[] notes = request.getParameterValues("note_l");
        String[] files = request.getParameterValues("file_l");
        List<ConsumeDO> consumeDOS = new LinkedList<>();
        for (int i = 0; i < consumeTypes.length; i++) {
            try {
                ConsumeDO consumeDO = new ConsumeDO();
                consumeDO.setConsumeId(consumeIds[i]);
                consumeDO.setConsumeType(consumeTypes[i]);
                consumeDO.setPayer(payers[i]);
                consumeDO.setTime(java.sql.Date.valueOf(time[i]));
                consumeDO.setAmount(new BigDecimal(amounts[i]));
                consumeDO.setInvoiceNum(Integer.parseInt(invoiceNums[i]));
                consumeDO.setNote(notes[i]);
//                consumeDO.setFile(files[i]);
                consumeDOS.add(consumeDO);
            } catch (IllegalArgumentException ignored) {
            }
        }
        String[] loanId = request.getParameterValues("loanId");
        String[] loanCode = request.getParameterValues("loanCode");
        String[] state = request.getParameterValues("state_l");
        String[] date = request.getParameterValues("date");
        String[] reason = request.getParameterValues("reasonn");
        String[] loanAmount = request.getParameterValues("loanAmount");
        List<LoanDO> loanDOS = new LinkedList<>();
        for (int i = 0; i < loanId.length; i++) {
            try {
                LoanDO loanDO = new LoanDO();
                loanDO.setLoanId(loanId[i]);
                loanDO.setLoanCode(loanCode[i]);
                loanDO.setAmount(new BigDecimal(loanAmount[i]));
                loanDO.setDate(date[i]);
                loanDO.setReason(reason[i]);
                loanDO.setState(Integer.parseInt(state[i]));
                loanDOS.add(loanDO);
            } catch (IllegalArgumentException ignored) {
            }
        }
        reimbursement.setConsume(consumeDOS);
        reimbursement.setLoan(loanDOS);
        if (reimbursementService.save(reimbursement) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("reimburse:reimbursement:edit")
    public R update(ReimbursementDO reimbursement, HttpServletRequest request) throws Exception {
        String[] consumeIds = request.getParameterValues("consumeId");
        String[] consumeTypes = request.getParameterValues("consumeType");
        String[] payers = request.getParameterValues("payer");
        String[] time = request.getParameterValues("time");
        String[] amounts = request.getParameterValues("consumeAmount");
        String[] invoiceNums = request.getParameterValues("invoiceNumber");
        String[] notes = request.getParameterValues("note_l");
        String[] files = request.getParameterValues("file_l");
        List<ConsumeDO> consumeDOS = new LinkedList<>();
        for (int i = 0; i < consumeTypes.length; i++) {
            try {
                ConsumeDO consumeDO = new ConsumeDO();
                consumeDO.setConsumeId(consumeIds[i]);
                consumeDO.setConsumeType(consumeTypes[i]);
                consumeDO.setPayer(payers[i]);
                consumeDO.setTime(java.sql.Date.valueOf(time[i]));
                consumeDO.setAmount(new BigDecimal(amounts[i]));
                consumeDO.setInvoiceNum(Integer.parseInt(invoiceNums[i]));
                consumeDO.setNote(notes[i]);
//                consumeDO.setFile(files[i]);
                consumeDOS.add(consumeDO);
            } catch (IllegalArgumentException ignored) {
            }
        }
        String[] loanId = request.getParameterValues("loanId");
        String[] loanCode = request.getParameterValues("loanCode");
        String[] state = request.getParameterValues("state_l");
        String[] date = request.getParameterValues("date");
        String[] reason = request.getParameterValues("reasonn");
        String[] loanAmount = request.getParameterValues("loanAmount");
        List<LoanDO> loanDOS = new LinkedList<>();
        for (int i = 0; i < loanId.length; i++) {
            try {
                LoanDO loanDO = new LoanDO();
                loanDO.setLoanId(loanId[i]);
                loanDO.setLoanCode(loanCode[i]);
                loanDO.setAmount(new BigDecimal(loanAmount[i]));
                loanDO.setDate(date[i]);
                loanDO.setReason(reason[i]);
                loanDO.setState(Integer.parseInt(state[i]));
                loanDOS.add(loanDO);
            } catch (IllegalArgumentException ignored) {
            }
        }
        reimbursement.setConsume(consumeDOS);
        reimbursement.setLoan(loanDOS);
        if (reimbursementService.update(reimbursement) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("reimburse:reimbursement:remove")
    public R remove(String reimburseId) {
        if (reimbursementService.remove(reimburseId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("reimburse:reimbursement:batchRemove")
    public R remove(@RequestParam("ids[]") String[] reimburseIds) {
        reimbursementService.batchRemove(reimburseIds);
        return R.ok();
    }

}
