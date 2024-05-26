package eduin.courseapi.TopicDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TopicDTO {
    @NotBlank(message = "Topic is required")
    private int id;
    @NotBlank(message = "Topic is required")
    private String name;
    @NotBlank(message = "Topic is required")
    @Size(min = 35, max = 150, message = "Description must be minimum 15 characters")
    private String Description;

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

}