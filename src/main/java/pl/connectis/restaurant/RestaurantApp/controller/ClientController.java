package pl.connectis.restaurant.RestaurantApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.RestaurantApp.dto.ClientDTO;
import pl.connectis.restaurant.RestaurantApp.exception.NotFoundException;
import pl.connectis.restaurant.RestaurantApp.model.Client;
import pl.connectis.restaurant.RestaurantApp.service.ClientService;

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(path = "/add")
    public Long addClient(@RequestBody ClientDTO clientDTO) {
        Long getId = clientService.addClient(
                clientDTO.getName(),
                clientDTO.getSurname(),
                clientDTO.getDiscount()
        );
        return getId;
    }

    @GetMapping(path = "/{id}")
    public ClientDTO getClient(@PathVariable("id") Long id) {
        Optional<Client> clientOptional = clientService.getClient(id);

        if (clientOptional.isPresent()) {
            ClientDTO clientDTO = clientService.toDTO(clientOptional.get());
            return clientDTO;
        }
        throw new NotFoundException();
    }

    @PostMapping(path = "/update/{id}")
    public String updateClient(@PathVariable("id") Long id, @RequestParam Double discount) {
        Optional<Client> clientOptional = clientService.getClient(id);
        if (clientOptional.isPresent()) {
            clientService.updateClientDiscount(id, discount);
            return "Updated Client";
        }
        throw new NotFoundException();
    }

    @DeleteMapping(path = "/{id}")
    public String deleteClient(@PathVariable("id") Long id) {
        Optional<Client> clientOptional = clientService.getClient(id);
        if (clientOptional.isPresent()) {
            clientService.removeClient(id);
            return "Removed client";
        }
        throw new NotFoundException();
    }
}
