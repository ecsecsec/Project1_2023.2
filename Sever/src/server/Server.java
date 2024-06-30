package server;
import java.net.*;
import java.io.*;
import java.sql.*;
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Connection conn;
    private Statement stmt;

    public Server() throws IOException, SQLException {
        serverSocket = new ServerSocket(8000);
        System.out.println("Server started. Listening for incoming connections...");

        // Connect to database
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "password";
        conn = DriverManager.getConnection(url, username, password);
        stmt = conn.createStatement();
    }

    public void startServer() throws IOException, SQLException {
        while (true) {
            clientSocket = serverSocket.accept();
            System.out.println("Incoming connection from " + clientSocket.getInetAddress());

            // Handle client request
            handleRequest(clientSocket);
        }
    }

    private void handleRequest(Socket clientSocket) throws IOException, SQLException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String request = in.readLine();
        System.out.println("Received request: " + request);

        // Process request
        if (request.startsWith("SELECT")) {
            ResultSet resultSet = stmt.executeQuery(request);
            out.println("Result:");
            while (resultSet.next()) {
                out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        } else if (request.startsWith("INSERT")) {
            stmt.executeUpdate(request);
            out.println("Insert successful!");
        } else {
            out.println("Invalid request");
        }

        clientSocket.close();
    }

    public static void main(String[] args) throws IOException, SQLException {
        Server server = new Server();
        server.startServer();
    }
}
