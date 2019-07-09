package com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.Paper;
import com.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @RequestMapping("/allPaper")
    public String list(Model model) {
        List<Paper> list = paperService.queryAllPaper();
        model.addAttribute("list", list);
        return "allPaper";
    }

    @RequestMapping("toAddPaper")
    public String toAddPaper() {
        return "addPaper";
    }

    @RequestMapping("/addPaper")
    public String addPaper(Paper paper) {
        paperService.addPaper(paper);
        return "redirect:/paper/allPaper";
    }

    @RequestMapping("/del/{paperId}")
    public String deletePaper(@PathVariable("paperId") Long id) {
        paperService.deletePaperById(id);
        return "redirect:/paper/allPaper";
    }

    @RequestMapping("toUpdatePaper")
    public String toUpdatePaper(Model model, Long id) {
        model.addAttribute("paper", paperService.queryById(id));
        return "updatePaper";
    }

    @RequestMapping("/updatePaper")
    public String updatePaper(Model model, Paper paper) {
        paperService.updatePaper(paper);
        paper = paperService.queryById(paper.getPaperId());
        model.addAttribute("paper", paper);
        return "redirect:/paper/allPaper";
    }

    /**
     * ModelAndView返回视图
     *
     * @param model
     * @param pagenum
     * @param pagesize
     * @return
     */
    @RequestMapping("/getPaperPage")
    public ModelAndView getPaperPage(ModelAndView model, @RequestParam(value = "pagenum") int pagenum, @RequestParam(value = "pagesize") int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        List<Paper> list = paperService.queryAllPaper();
        PageInfo<Paper> info = new PageInfo<Paper>(list);
        List<Paper> paperlist = info.getList();
        model.addObject("paperlist", paperlist);
        model.setViewName("pagepaper");
        return model;
    }

    /**
     * @param model
     * @param pagenum
     * @param pagesize
     * @return
     */
    @RequestMapping("/getPaperPage2")
    public String getPaperPage2(Model model, @RequestParam(value = "pagenum") int pagenum, @RequestParam(value = "pagesize") int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        List<Paper> papers = paperService.queryAllPaper();
        PageInfo<Paper> info = new PageInfo<Paper>(papers);
        List<Paper> paperlist = info.getList();
        model.addAttribute("paperlist", paperlist);
        return "pagepaper";
    }

}