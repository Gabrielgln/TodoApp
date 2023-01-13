package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author Marcio Michelluzzi
 */
public class TaskController {

    public void save(Task task) {
        String sql = "INSERT INTO tasks(idProject, name, description, completed, notes, deadline, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            //Cria uma conex?o com o banco
            conn = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            statement = conn.prepareStatement(sql);

            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new java.sql.Date(task.getDeadline().getTime()));
            statement.setDate(7, new java.sql.Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new java.sql.Date(task.getUpdatedAt().getTime()));

            //Executa a sql para inser??o dos dados
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);
        } finally {
            //Fecha as conex?es
            ConnectionFactory.closeConnection(conn, statement);
        }

    }

    public void update(Task task) {

        String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?, completed = ?, notes = ?, deadline = ?, createdAt = ?, updatedAt = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            //Cria uma conex?o com o banco
            conn = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            statement = conn.prepareStatement(sql);

            statement.setInt     (1, task.getIdProject());
            statement.setString  (2, task.getName());
            statement.setString  (3, task.getDescription());
            statement.setBoolean (4, task.getCompleted());
            statement.setString  (5, task.getNotes());
            statement.setDate    (6, new java.sql.Date(task.getDeadline().getTime()));
            statement.setDate    (7, new java.sql.Date(task.getCreatedAt().getTime()));
            statement.setDate    (8, new java.sql.Date(task.getUpdatedAt().getTime()));
            statement.setInt     (9, task.getId());

            //Executa a sql para inser??o dos dados
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro em atualizar a tarefa", ex);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
          
        }
    }

    public List<Task> getAll() {
        String sql = "SELECT * FROM tasks";

        List<Task> tasks = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);

            resultSet = statement.executeQuery();

            //Enquanto existir dados no banco de dados, fa?a
            while (resultSet.next()) {

                Task task = new Task();

                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setNotes(resultSet.getString("notes"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setCreatedAt(resultSet.getDate("updatedAt"));

                //Adiciono o contato recuperado, a lista de contatos
                tasks.add(task);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar as tarefas", ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
        return tasks;
    }

    public List<Task> getByProjectId(int id) {
        String sql = "SELECT * FROM tasks where idProject = ?";
        
        //criando uma lista de tarefas
        List<Task> tasks = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            
            // o statement vai receber o id passado no parametro
            statement.setInt(1, id);
            
            //o result set pegou esse id do statement e executou a query
            resultSet = statement.executeQuery();

            //Enquanto existir dados no banco de dados, fa?a
            //vai percorrer o result set até achar o id que ta nele:
            while (resultSet.next()) {
                
                //criando uma nova tarefa
                Task task = new Task();

                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setCompleted(resultSet.getBoolean("completed"));  
                task.setNotes(resultSet.getString("notes"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setCreatedAt(resultSet.getDate("updatedAt"));

                //Adiciono o contato recuperado, a lista de contatos
                tasks.add(task);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar as tarefas", ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
        //retorna para o usuario a tarefa!
        return tasks;
    }

    public void removeById(int id) {

        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar a tarefa", ex);
        } finally {
            ConnectionFactory.closeConnection(conn,statement);  
        }

    }

}

