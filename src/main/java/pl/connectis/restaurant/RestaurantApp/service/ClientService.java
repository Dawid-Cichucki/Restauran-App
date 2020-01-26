package pl.connectis.restaurant.RestaurantApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.connectis.restaurant.RestaurantApp.dto.ClientDTO;
import pl.connectis.restaurant.RestaurantApp.exception.BadRequestException;
import pl.connectis.restaurant.RestaurantApp.model.Client;
import pl.connectis.restaurant.RestaurantApp.repositories.ClientRepository;

import java.util.Optional;

import static pl.connectis.restaurant.RestaurantApp.validation.ValidationClass.*;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Long addClient(String name, String surname, Double discount) {
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);
        client.setDiscount(discount);

        validateClient(client);

        clientRepository.save(client);

        return client.getId();
    }

    public void updateClientDiscount(Long id, Double discount) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setDiscount(discount);

            validateClient(client);

            clientRepository.save(client);
            return;
        }
    }

    public void removeClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Optional<Client> getClient(Long id) {
        return clientRepository.findById(id);
    }

    private void validateClient(Client client) {
        if (isNullOrEmpty(client.getName()) ||
                isNullOrEmpty(client.getSurname()) ||
                isNull(client.getDiscount())) {
            throw new BadRequestException();
        }
    }

    public ClientDTO toDTO(Client client){
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setDiscount(client.getDiscount());
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setSurname(client.getSurname());

        return clientDTO;
    }

    public Client toModel(ClientDTO clientDTO){
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setSurname(clientDTO.getSurname());
        client.setDiscount(clientDTO.getDiscount());

        return client;
    }
}
