package com.example.aad_pos_system.Controller;



import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.aad_pos_system.Bo.Impl.ItemBoImpl;
import com.example.aad_pos_system.Dao.Impl.ItemDaoImpl;
import com.example.aad_pos_system.Dto.ItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/item")
public class ItemController extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(ItemController.class);
    ItemBoImpl itemBo =new ItemBoImpl();
    Connection connection;
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
        List<ItemDto> allItems = itemBo.getAllItems(connection);
        try (var writer = resp.getWriter()){
            logger.info("get all Items");
            resp.setContentType("application/json");
            Jsonb jsonb = JsonbBuilder.create();
            jsonb.toJson(allItems,writer);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().startsWith("application/json")||req.getContentType()==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try (var writer = resp.getWriter()){
            logger.info("Save Items");
            Jsonb jsonb = JsonbBuilder.create();
            ItemDto itemDto = jsonb.fromJson(req.getReader(), ItemDto.class);
            System.out.println(itemDto);
            boolean saveItem = itemBo.SaveItem(itemDto,connection);
            if (saveItem){
                writer.write("item Saved Success"+itemDto);
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else {
                writer.write("Try again");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {
            logger.info("Update Items");
            Jsonb jsonb = JsonbBuilder.create();
            ItemDto itemDto = jsonb.fromJson(req.getReader(), ItemDto.class);
            boolean UpdateItem = itemBo.Update(itemDto,connection);

            if (UpdateItem){
                writer.write("Update SuccessFull!!"+itemDto);
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            }else {
                writer.write("Try again!");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        try (var writer = resp.getWriter()){
            logger.info("Delete Items");
            boolean deleteItem = itemBo.deleteItem(code,connection);
            if (deleteItem){
                writer.write("Delete success  "+code);
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            }else {
                writer.write("try again!");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
