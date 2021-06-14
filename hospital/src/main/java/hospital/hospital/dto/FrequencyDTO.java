package hospital.hospital.dto;

public class FrequencyDTO {

    private int times;

    private int seconds;

    public FrequencyDTO(int times, int seconds) {
        this.times = times;
        this.seconds = seconds;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
