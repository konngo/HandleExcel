package cn.konngo;

import java.util.Arrays;

/**
 * 考勤表
 * www.konngo.cn
 */
public class Attence {
    // 工号
    private String ano;
    // 姓名
    private String name;
    // 部门
    private  String dep;
    // 日期
    private String dates;
    // 考勤时间
    private String times[];

    public String[] getTimes() {
        return times;
    }

    public void setTimes(String[] times) {
        this.times = times;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    @Override
    public String toString() {
        return "Attence{" +
                "ano='" + ano + '\'' +
                ", name='" + name + '\'' +
                ", dep='" + dep + '\'' +
                ", dates='" + dates + '\'' +
                ", times=" + Arrays.toString(times) +
                '}';
    }
}
