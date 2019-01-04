package MainApp.DTOs;

public class CreateVM_DTO {

    public String name;
    public String memory;
    public String disk_storage;

    public CreateVM_DTO(String name, String memory, String disk_storage) {
        this.name = name;
        this.memory = memory;
        this.disk_storage = disk_storage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getDisk_storage() {
        return disk_storage;
    }

    public void setDisk_storage(String disk_storage) {
        this.disk_storage = disk_storage;
    }
}
