package com.zy.web.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListParm {

    private List<Long> id;

    private List<Long> yid;

    private List<Long> rid;

    //private List<Long> aid;

    private String vname;

    private String vphoto;

    private String vurl;

    private String vinfo;

    private String typeName;

    private String region;

    private String videoYear;


}
