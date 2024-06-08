package com.example.conecta_test.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
@Schema(
        description = "CarDto Model Information"
)
public class CarDto {

    private Long id;


    @NotEmpty(message = "El campo plate (placa) es oplate (placa)bligatorio")
    @Pattern(regexp = "^[A-Z]{3}\\d{3,4}$", message = "El campo plate (placa) debe seguir el formato 'XXX000' o 'XXX0000'")
    private String plate;

    @NotEmpty(message = "El color es obligatorio")
    private String color;

    @NotEmpty(message = "El campo automaker (marca) es obligatorio")
    private String automaker;

    @NotEmpty(message = "El campo model (modelo) es obligatorio")
    private String model;

    @NotEmpty(message = "El campo vim (número de chasis) es obligatorio")
    @Size(min=17, max = 17, message = "El vim (número de chasis) es obligatorio")
    private String vim;

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAutomaker(String automaker) {
        this.automaker = automaker;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVim(String vim) {
        this.vim = vim;
    }

    public String getPlate() {
        return plate;
    }

    public String getColor() {
        return color;
    }

    public String getAutomaker() {
        return automaker;
    }

    public String getModel() {
        return model;
    }

    public String getVim() {
        return vim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
