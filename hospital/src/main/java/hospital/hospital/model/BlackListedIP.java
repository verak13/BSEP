package hospital.hospital.model;

import java.util.List;

public class BlackListedIP {

    private List<String> list;

    public BlackListedIP(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
