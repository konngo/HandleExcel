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
        // 去掉 时间 类似：03:32:12 最开始的0
        for (int i = 0; i < times.length; i++) {
            if (times[i].indexOf("0")==0){
                times[i]=times[i].substring(1);
            }
        }
        this.times = times;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        // 对日期格式进行修改
        // - 换成  /
        this.dates = dates.replaceAll("-", "/");
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
