package pl.connectis.restaurant.RestaurantApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.RestaurantApp.dto.ClientDTO;
import pl.connectis.restaurant.RestaurantApp.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(path = "/add")
    public Long addClient(@RequestBody ClientDTO clientDTO){
        Long getId = clientService.addClient(
                clientDTO.getName(),
                clientDTO.getSurname(),
                clientDTO.getDiscount()
        );
        return getId;
    }

    @GetMapping(path = "/get/{id}")
    public ClientDTO getClient(@PathVariable("id") Long id){
        ClientDTO clientDTO = new ClientDTO(clientService.getClient(id).get());
        return clientDTO;
    }

    @PutMapping(path = "/update/{id}")
    public String updateClient(@PathVariable("id") Long id,  @RequestParam Double discount){
        clientService.updateClientDiscount(id, discount);
        return "Updated Client";
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteClient(@PathVariable("id") Long id){
        clientService.removeClient(id);
        return "Removed client";
    }
}
