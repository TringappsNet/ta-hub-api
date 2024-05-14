package tahub.sdapitahub.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import tahub.sdapitahub.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ClientMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client.Builder()
                .clientId(rs.getLong("client_id"))
                .clientName(rs.getString("client_name"))
                .clientSpocName(rs.getString("client_spoc_name"))
                .clientSpocContact(rs.getString("client_spoc_contact"))
                .clientLocation(rs.getString("client_location"))
                .createdAt(rs.getObject("created_at", LocalDateTime.class))
                .lastUpdated(rs.getObject("last_updated", LocalDateTime.class))
                .build();
        return client;
    }
}

