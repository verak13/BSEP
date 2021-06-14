package hospital.hospital.dto;

import java.util.ArrayList;

public class TypeList {

    private ArrayList<String> list;

    public TypeList() {};

    public TypeList(ArrayList<String> list) {
        this.list = list;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setTList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        if (this.list == null) {
            return "()";
        }
        String res = "(";
        for (String t: this.list) {
            res += "\"" + t + "\",";
        }
        res = res.substring(0, res.length() - 1);
        res += ")";

        return res;
    }
}
