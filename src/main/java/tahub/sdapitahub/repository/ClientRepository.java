package tahub.sdapitahub.repository;

import tahub.sdapitahub.entity.Client;
import tahub.sdapitahub.repository.mapper.Client.ClientMapper;
import tahub.sdapitahub.repository.mapper.Client.ClientViewMapper;
import tahub.sdapitahub.repository.query.ClientQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Client> findAll() {
        return jdbcTemplate.query(ClientQuery.FIND_ALL.getQuery(), new ClientMapper());
    }

    public List<Client> ClientViewAll() {
        return jdbcTemplate.query(ClientQuery.CLIENT_VIEW.getQuery(), new ClientViewMapper());
    }

    public Client findById(Long id) {
        return jdbcTemplate.queryForObject(ClientQuery.FIND_BY_ID.getQuery(), new Object[]{id}, new ClientMapper());
    }

    public Client save(Client client) {
        jdbcTemplate.update(ClientQuery.SAVE.getQuery(),
                client.getClientName(),

                client.getClientSpocName(),
                client.getClientSpocContact(),
                client.getClientLocation(),
                client.getCreatedAt(),
                client.getLastUpdated());
        return client;
    }

    public Client update(Client client) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE ta_client_details SET ");
        List<Object> queryParams = new ArrayList<>();

        // Initialize a flag to track if any fields are updated
        boolean fieldsUpdated = false;

        if (client.getClientName() != null){
            queryBuilder.append("client_name = ?, ");
            queryParams.add(client.getClientName());
            fieldsUpdated = true;
        }
        if (client.getClientSpocName() != null) {
            queryBuilder.append("client_spoc_name = ?, ");
            queryParams.add(client.getClientSpocName());
            fieldsUpdated = true;
        }
        if (client.getClientSpocContact() != null) {
            queryBuilder.append("client_spoc_contact = ?, ");
            queryParams.add(client.getClientSpocContact());
            fieldsUpdated = true;
        }
        if (client.getClientLocation() != null) {
            queryBuilder.append("client_location = ?, ");
            queryParams.add(client.getClientLocation());
            fieldsUpdated = true;
        }
        if (client.getLastUpdated() != null) {
            queryBuilder.append("last_updated = ?, ");
            queryParams.add(client.getLastUpdated());
            fieldsUpdated = true;
        }

        if (fieldsUpdated) {
            queryBuilder.setLength(queryBuilder.length() - 2); // Remove the trailing comma and space
            queryBuilder.append(" WHERE client_id = ?");
            queryParams.add(client.getClientId());

            jdbcTemplate.update(queryBuilder.toString(), queryParams.toArray());
        }
        return client;
    }


    public void deleteById(Long id) {
        jdbcTemplate.update(ClientQuery.DELETE_BY_ID.getQuery(), id);
    }



}
