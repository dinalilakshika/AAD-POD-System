package com.example.aad_pos_system.Controller;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.aad_pos_system.Bo.Impl.OrderBoImpl;
import com.example.aad_pos_system.Dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/orders")
public class OrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(OrderController.class);
    Connection connection;
    OrderBoImpl orderBo = new OrderBoImpl();
    @Override
    public void init() throws ServletException {
        try {
            InitialContext initialContext = new InitialContext();
            DataSource lookup = (DataSource) initialContext.lookup("java:comp/env/jdbc/stuRegistration");
            this.connection = lookup.getConnection();
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newOrderId = generateNewOrderId();
        resp.setContentType("application/json");
        try (PrintWriter writer = resp.getWriter()) {
            JsonObject responseJson = Json.createObjectBuilder()
                    .add("orderId", newOrderId)
                    .build();
            writer.write(responseJson.toString());
        }
    }

    private String generateNewOrderId() {
        // Fetch the latest order ID from the database and increment it
        String lastOrderId = getLastOrderIdFromDatabase();
        int newIdNumber = Integer.parseInt(lastOrderId.replace("OR-", "")) + 1;
        return "OR-" + String.format("%03d", newIdNumber);
    }

    private String getLastOrderIdFromDatabase() {
        String lastOrderId = "OR-000"; // Default value if no orders exist

        // Query to get the last order ID
        String query = "SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lastOrderId = resultSet.getString("orderId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastOrderId;
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().startsWith("application/json")||req.getContentType()==null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try (var writer = resp.getWriter()){
            logger.info("Save Order");
            Jsonb jsonb = JsonbBuilder.create();
            OrderDto orderDto = jsonb.fromJson(req.getReader(), OrderDto.class);
            System.out.println(orderDto);
            boolean saveOrder = orderBo.SaveOrder(orderDto,connection);
            if (saveOrder){
                writer.write("Order saved");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else {
                writer.write("Error saving Order");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
