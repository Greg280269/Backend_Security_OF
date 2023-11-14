package pe.edu.upc.aaw.proyecto_awb.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pe.edu.upc.aaw.proyecto_awb.dtos.RoleDTO;
import pe.edu.upc.aaw.proyecto_awb.entities.Roles;
import pe.edu.upc.aaw.proyecto_awb.serviceinterfaces.IRoleServices;
import pe.edu.upc.aaw.proyecto_awb.serviceinterfaces.IUserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private IRoleServices irS;
    @Autowired
    private IUserService iuS;

    @PostMapping
    public void insert(@RequestBody RoleDTO dto) {
        ModelMapper m = new ModelMapper();
        Roles b = m.map(dto, Roles.class);
        irS.insertar(b);
    }
    @GetMapping
    public List<RoleDTO> list() {
        return irS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RoleDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")int id){
        irS.delete(id);
    }

    @GetMapping("/{id}")
    public RoleDTO listId(@PathVariable("id")int id){
        ModelMapper m=new ModelMapper();
        RoleDTO dto=m.map(irS.listId(id),RoleDTO.class);
        return dto;
    }

    @PutMapping
    public void goUpdate(@RequestBody RoleDTO dto){
        ModelMapper m=new ModelMapper();
        Roles a=m.map(dto,Roles.class);
        irS.insertar(a);
    }
}
