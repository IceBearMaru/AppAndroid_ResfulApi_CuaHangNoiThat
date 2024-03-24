////package Controller;
////
////import DAO.ProductDAO;
////import Model.Product_Ett;
////
////import javax.servlet.RequestDispatcher;
////import javax.servlet.ServletException;
////import javax.servlet.annotation.WebServlet;
////import javax.servlet.http.HttpServlet;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////import java.io.IOException;
////import java.sql.SQLException;
////import java.util.ArrayList;
////import java.util.List;
////
////@WebServlet("/Trang-chu-client")
////public class Home_Client_Control extends HttpServlet {
////    private static final long serialVersionUID = 1L;
////
////    public Home_Client_Control() {
////        super();
////    }
////
////    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        ProductDAO productDAO = new ProductDAO();
////        List<Product_Ett> list= new ArrayList<Product_Ett>();
////		try {
////			list = productDAO.getAll();
////		} catch (ClassNotFoundException | SQLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////        request.setAttribute("products", list);
////
////        // JSP (index.jsp)
////        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
////        rd.forward(request, response);
////    }
////
////}
////
////
////
////    
////    
////    
////    // TEST DỮ LIỆU
////
//////    // Added main method for testing
//////    public static void main(String[] args) throws ClassNotFoundException {
//////        Product_Controller controller = new Product_Controller();
//////        List<Product_Ett> productList = controller.getAllProductsForTesting();
//////
//////        for (Product_Ett product : productList) {
//////            System.out.println(product.getId() + " | " + product.getName() + " | " + product.getMaterial()
//////                    + " | " + product.getOrigin() + " | " + product.getImageUrl() + " | " + product.getPrice());
//////        }
//////    }
//////
//////    // Method for testing without servlet environment
//////    private List<Product_Ett> getAllProductsForTesting() throws ClassNotFoundException {
//////        ProductDAO productDAO = new ProductDAO();
//////        return productDAO.getAllProducts();
//////    }
////
//
//
//
//<?xml version="1.0" encoding="UTF-8"?>
//<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
//	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
//	version="4.0">
//	
//	  	<!-- Welcome File List -->
//	<welcome-file-list>
//		<welcome-file>account.html</welcome-file>
//	</welcome-file-list>
//	<display-name>Archetype Created Web Application</display-name>
//
//
//	<!-- Configuration for Jersey -->
//	<servlet>
//		<servlet-name>Product_Controller</servlet-name>
//		<servlet-class>Controller.Product_Controller</servlet-class>
//	</servlet>
//	 <servlet-mapping>
//  	<servlet-name>IndexController</servlet-name>
//  	<url-pattern>/Product_Controller</url-pattern>
//   </servlet-mapping>
//   
//	<servlet>
//        <servlet-name>JerseyRESTService</servlet-name>
//        <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
//        <init-param>
//            <param-name>jersey.config.server.provider.packages</param-name>
//            <param-value>Resful_Api</param-value>
//        </init-param>
//        <load-on-startup>1</load-on-startup>
//    </servlet>
//    <servlet-mapping>
//        <servlet-name>JerseyRESTService</servlet-name>
//        <url-pattern>/rest/*</url-pattern>
//    </servlet-mapping>
//    
//  
//
//</web-app>
//
//
//
//
//<project xmlns="http://maven.apache.org/POM/4.0.0"
//	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
//	<modelVersion>4.0.0</modelVersion>
//	<groupId>com.cgm</groupId>
//	<artifactId>hello-web-app</artifactId>
//	<packaging>war</packaging>
//	<version>0.0.1-SNAPSHOT</version>
//	<name>hello-web-app Maven Webapp</name>
//	<url>http://maven.apache.org</url>
//	<properties>
//		<maven.compiler.source>1.8</maven.compiler.source>
//		<maven.compiler.target>1.8</maven.compiler.target>
//	</properties>
//	<dependencies>
//
//		<dependency>
//			<groupId>javax.servlet</groupId>
//			<artifactId>javax.servlet-api</artifactId>
//			<version>4.0.1</version>
//			<scope>provided</scope>
//		</dependency>
//
//		<dependency>
//			<groupId>org.apache.taglibs</groupId>
//			<artifactId>taglibs-standard-spec</artifactId>
//			<version>1.2.5</version>
//		</dependency>
//		<dependency>
//			<groupId>org.apache.taglibs</groupId>
//			<artifactId>taglibs-standard-impl</artifactId>
//			<version>1.2.5</version>
//		</dependency>
//
//		<!--Driver-->
//		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
//		<dependency>
//			<groupId>mysql</groupId>
//			<artifactId>mysql-connector-java</artifactId>
//			<version>8.0.33</version>
//		</dependency>
//
//		<dependency>
//			<groupId>org.glassfish.jersey.containers</groupId>
//			<artifactId>jersey-container-servlet</artifactId>
//			<version>2.29.1</version>
//		</dependency>
//
//		<dependency>
//			<groupId>org.glassfish.jersey.inject</groupId>
//			<artifactId>jersey-hk2</artifactId>
//			<version>2.29.1</version>
//		</dependency>
//
//		<dependency>
//			<groupId>org.glassfish.jersey.media</groupId>
//			<artifactId>jersey-media-json-jackson</artifactId>
//			<version>2.29.1</version>
//		</dependency>
//
//		<dependency>
//			<groupId>com.thoughtworks.xstream</groupId>
//			<artifactId>xstream</artifactId>
//			<version>1.4.18</version> <!-- Use the latest version available -->
//		</dependency>
//
//
//	</dependencies>
//
//		<build>
//		<plugins>
//			<plugin>
//				<groupId>org.apache.maven.plugins</groupId>
//				<artifactId>maven-war-plugin</artifactId>
//				<version>3.3.1</version>
//				<configuration>
//					<!-- Add any configuration specific to the maven-war-plugin here -->
//				</configuration>
//			</plugin>
//		</plugins>
//		<finalName>hello-web-app</finalName>
//	</build>
//	
//</project>
//
