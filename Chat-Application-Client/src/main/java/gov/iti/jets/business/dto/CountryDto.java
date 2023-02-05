package gov.iti.jets.business.dto;

public class CountryDto {

    private String name;
    private int count;

    public CountryDto(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public CountryDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CountryDto [name=" + name + ", count=" + count + "]";
    }

}
