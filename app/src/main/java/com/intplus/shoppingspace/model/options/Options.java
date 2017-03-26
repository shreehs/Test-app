package com.intplus.shoppingspace.model.options;

import java.util.ArrayList;

/**
 * Created by harshas on 3/25/2017.
 */
// Options POJO
public class Options {

    private Integer oid; // Option id
    private Integer optionType;
    private String title;
    private String subTitle;
    private Boolean cbEnable;

    public Options(Integer oid, Integer optionType, String title, String subTitle){
        this.oid = oid;
        this.optionType = optionType;
        this.title = title;
        this.subTitle = subTitle;
        this.cbEnable = null;
    }

    public Options(Integer oid, Integer optionType, String title, String subTitle, Boolean cbEnable){
        this.oid = oid;
        this.optionType = optionType;
        this.title = title;
        this.subTitle = subTitle;
        this.cbEnable = cbEnable;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public int getOptionType() {
        return optionType;
    }

    public void setOptionType(int optionType) {
        this.optionType = optionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public boolean isCbEnable() {
        return cbEnable;
    }

    public void setCbEnable(boolean cbEnable) {
        this.cbEnable = cbEnable;
    }

    public static ArrayList<Options> getAllOptions(){
        ArrayList<Options> optionsList = new ArrayList<>();
        optionsList.add(new Options(1, 1, "Delegate to native app",
                "If native app is present, it will be used to open the shop", true));
        optionsList.add(new Options(2, 1, "Remember login credentials",
                "Login credentials for each app will be stored encrypted in the device", true));
        optionsList.add(new Options(3, 2, "Rate app",
                "We would love to collect a 5 star and a good comment from you!"));
        optionsList.add(new Options(4, 2, "Like on facebook",
                "Please like Shopping Space facebook and help grow the community :)"));
        return optionsList;
    }

}
