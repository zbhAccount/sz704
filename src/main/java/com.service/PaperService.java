package com.service;

import com.pojo.Paper;

import java.util.List;

//alt+enter  快速导包

public interface PaperService {

    int addPaper(Paper paper);

    int deletePaperById(long id);

    int updatePaper(Paper paper);

    Paper queryById(long id);

    List<Paper> queryAllPaper();

}
