/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.model.Evento;
import grupo3.reto2.model.Lugar;
import grupo3.reto2.model.User;
import static grupo3.reto2.model.UserPrivilege.ADMIN;
import static grupo3.reto2.model.UserPrivilege.CLIENT;
import grupo3.reto2.logic.EventManagerFactory;
import grupo3.reto2.cipher.Mail;
import grupo3.reto2.exception.CreateException;
import grupo3.reto2.exception.DeleteException;
import grupo3.reto2.exception.ReadException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericType;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ale
 */
public class EventController extends GenericController {

    private EventManagerFactory emf = new EventManagerFactory();

    private Evento e = new Evento();

    //private User user;
    @FXML
    private Button btnCrear;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnInforme;

    @FXML
    private Button btnayuda;

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnSubscribir;

    @FXML
    private Button btnBuscar;

    @FXML
    private TextField tfDescripcion;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextField tfTipoEvento;

    @FXML
    private TextField tfPremio;

    @FXML
    private TextField tfIdLugar;

    @FXML
    private TextField tfFiltro;

    @FXML
    private ComboBox cbFiltro;

    @FXML
    private TableView<Evento> tvTablaEvento;

    @FXML
    private TableColumn tcIdEvento;

    @FXML
    private TableColumn tcDescripcion;

    @FXML
    private TableColumn tcFecha;

    @FXML
    private TableColumn tcPremio;

    @FXML
    private TableColumn tcTipoEvento;

    @FXML
    private TableColumn tcIdLugar;

    private ObservableList<Evento> eventsData;

    private static final Logger LOGGER = Logger.getLogger("/controller/EventController");

    public void initStage(Parent root) throws WebApplicationException, ReadException {
        User user = new User();
        user.setPrivilege(ADMIN);
        if (user.getPrivilege() == user.getPrivilege().ADMIN) {
            LOGGER.info("Iniciando método init stage");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Gestión de eventos");
            stage.setResizable(false);
            stage.setOnShowing(this::vistaVentana);

            tfDescripcion.textProperty().addListener(this::cambioDeTexto);
            btnModificar.setOnAction(this::modificar);
            dpFecha.promptTextProperty().addListener(this::cambioDeTexto);
            tfTipoEvento.textProperty().addListener(this::cambioDeTexto);
            tfPremio.textProperty().addListener(this::cambioDeTexto);
            tfIdLugar.textProperty().addListener(this::cambioDeTexto);

            btnBuscar.setOnAction(this::buscar);
            btnCerrar.setOnAction(this::salir);
            btnCrear.setOnAction(this::crear);
            btnSubscribir.setOnAction(this::subscribirse);
            btnBorrar.setOnAction(this::borrar);
            btnInforme.setOnAction(this::informe);
            btnayuda.setOnAction(this::ayuda);

            tvTablaEvento.getSelectionModel().selectedItemProperty().addListener(this::handleTableSelection);
            tcIdEvento.setCellValueFactory(new PropertyValueFactory<>("idEvento"));
            tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tcFecha.setCellFactory(column -> {
                TableCell<Evento, Date> cell = new TableCell<Evento, Date>() {
                    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                    @Override
                    protected void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            if (item != null) {
                                setText(format.format(item));
                            }
                        }
                    }
                };
                return cell;
            });
            tcPremio.setCellValueFactory(new PropertyValueFactory<>("premio"));
            tcTipoEvento.setCellValueFactory(new PropertyValueFactory<>("tipoEvento"));
            tcIdLugar.setCellValueFactory(new PropertyValueFactory<>("lugar"));

            eventsData = FXCollections.observableArrayList(emf.getFactory().viewEvents_XML(new GenericType<List<Evento>>() {
            }));

            tvTablaEvento.setItems(eventsData);

            stage.show();
        } else {
            LOGGER.info("Iniciando método init stage");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Gestión de eventos");
            stage.setResizable(false);
            stage.setOnShowing(this::vistaVentana);

            btnBuscar.setOnAction(this::buscar);
            btnCerrar.setOnAction(this::salir);
            btnSubscribir.setOnAction(this::subscribirse);
            btnInforme.setOnAction(this::informe);
            btnayuda.setOnAction(this::ayuda);

            tvTablaEvento.getSelectionModel().selectedItemProperty().addListener(this::handleTableSelection);
            tcIdEvento.setCellValueFactory(new PropertyValueFactory<>("idEvento"));
            tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tcFecha.setCellFactory(column -> {
                TableCell<Evento, Date> cell = new TableCell<Evento, Date>() {
                    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                    @Override
                    protected void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            if (item != null) {
                                setText(format.format(item));
                            }
                        }
                    }
                };
                return cell;
            });
            tcPremio.setCellValueFactory(new PropertyValueFactory<>("premio"));
            tcTipoEvento.setCellValueFactory(new PropertyValueFactory<>("tipoEvento"));
            tcIdLugar.setCellValueFactory(new PropertyValueFactory<>("lugar"));

            eventsData = FXCollections.observableArrayList(emf.getFactory().viewEvents_XML(new GenericType<List<Evento>>() {
            }));

            tvTablaEvento.setItems(eventsData);

            stage.show();
        }
    }

    @FXML
    private void vistaVentana(WindowEvent event) {
        User user = new User();
        user.setPrivilege(ADMIN);
        if (user.getPrivilege() == user.getPrivilege().ADMIN) {
            LOGGER.info("Iniciando método vista ventana");
            cbFiltro.setDisable(false);
            cbFiltro.getSelectionModel().getSelectedItem();
            tfFiltro.setDisable(true);
            tfFiltro.setVisible(false);
            btnBuscar.setVisible(false);
            btnBuscar.setDisable(true);
            btnCrear.setDisable(true);
            btnModificar.setDisable(true);
            btnBorrar.setDisable(true);
            btnSubscribir.setDisable(true);

            tfDescripcion.setPromptText("De que trata el evento...");
            dpFecha.setValue(LocalDate.of(2023, Month.JANUARY, 1));
            tfTipoEvento.setPromptText("Que de evento va a ser...");
            tfPremio.setPromptText("Cuál es el premio al ganador del evento...");
            tfIdLugar.setPromptText("Id del lugar en el que se realizará el evento...");

            cbFiltro.getItems().addAll(
                    "Filtrar por Id de evento",
                    "Filtrar por tipo de evento",
                    "Todos los eventos"
            );

            cbFiltro.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (null == cbFiltro.getValue().toString()) {
                        tfFiltro.setDisable(true);
                        btnBuscar.setVisible(false);
                    } else {
                        switch (cbFiltro.getValue().toString()) {
                            case "Filtrar por Id de evento":
                                tfFiltro.setDisable(false);
                                tfFiltro.setVisible(true);
                                btnBuscar.setVisible(true);
                                btnBuscar.setDisable(false);
                                break;
                            case "Filtrar por tipo de evento":
                                tfFiltro.setDisable(false);
                                tfFiltro.setVisible(true);
                                btnBuscar.setVisible(true);
                                btnBuscar.setDisable(false);
                                break;
                            default:
                                tfFiltro.setDisable(true);
                                tfFiltro.setVisible(false);
                                btnBuscar.setVisible(false);
                                btnBuscar.setDisable(true);
                                 {
                                    try {
                                        cargarDatos();
                                    } catch (WebApplicationException ex) {
                                        Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ReadException ex) {
                                        Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                        }
                    }
                }

            });
        } else {
            LOGGER.info("Iniciando método vista ventana");
            cbFiltro.setDisable(false);
            cbFiltro.getSelectionModel().getSelectedItem();
            tfFiltro.setDisable(true);
            tfFiltro.setVisible(false);
            btnBuscar.setVisible(false);
            btnBuscar.setDisable(true);
            btnCrear.setDisable(true);
            btnModificar.setDisable(true);
            btnBorrar.setDisable(true);
            btnSubscribir.setDisable(true);

            tfDescripcion.setDisable(true);
            dpFecha.setDisable(true);
            tfTipoEvento.setDisable(true);
            tfPremio.setDisable(true);
            tfIdLugar.setDisable(true);

            cbFiltro.getItems().addAll(
                    "Filtrar por Id de evento",
                    "Filtrar por tipo de evento",
                    "Todos los eventos"
            );

            cbFiltro.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (null == cbFiltro.getValue().toString()) {
                        tfFiltro.setDisable(true);
                        btnBuscar.setVisible(false);
                    } else {
                        switch (cbFiltro.getValue().toString()) {
                            case "Filtrar por Id de evento":
                                tfFiltro.setDisable(false);
                                tfFiltro.setVisible(true);
                                btnBuscar.setVisible(true);
                                btnBuscar.setDisable(false);
                                break;
                            case "Filtrar por tipo de evento":
                                tfFiltro.setDisable(false);
                                tfFiltro.setVisible(true);
                                btnBuscar.setVisible(true);
                                btnBuscar.setDisable(false);
                                break;
                            default:
                                tfFiltro.setDisable(true);
                                tfFiltro.setVisible(false);
                                btnBuscar.setVisible(false);
                                btnBuscar.setDisable(true);
                                 {
                                    try {
                                        cargarDatos();
                                    } catch (WebApplicationException ex) {
                                        Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ReadException ex) {
                                        Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                        }
                    }
                }

            });
        }
    }

    @FXML
    private void cambioDeTexto(ObservableValue observable, String oldValue, String newValue) {
        User user = new User();
        user.setPrivilege(ADMIN);
        if (user.getPrivilege() == user.getPrivilege().ADMIN) {
            String fecha = dpFecha.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (tfDescripcion.getText().trim().isEmpty() || tfTipoEvento.getText().trim().isEmpty() || tfPremio.getText().trim().isEmpty() || tfIdLugar.getText().trim().isEmpty()) {
                btnCrear.setDisable(true);
                btnModificar.setDisable(true);
                btnBorrar.setDisable(true);
            } else {
                btnCrear.setDisable(false);
                btnModificar.setDisable(false);
                btnBorrar.setDisable(false);

            }
        }
    }

    @FXML
    private void handleTableSelection(ObservableValue observable, Object oldValue, Object newValue) {
        User user = new User();
        user.setPrivilege(ADMIN);
        if (user.getPrivilege() == user.getPrivilege().ADMIN) {
            if (newValue != null) {
                Evento e = (Evento) newValue;
                tfDescripcion.setText(e.getDescripcion());
                tfTipoEvento.setText(e.getTipoEvento());
                tfPremio.setText(e.getPremio());
                tfIdLugar.setText(e.getLugar().toString());
                char c = tfIdLugar.getText().charAt(19);
                String s = String.valueOf(c);
                tfIdLugar.setText(s);
                dpFecha.setValue(e.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                btnSubscribir.setDisable(false);
                //pruebas peligrosas
            }
        } else {
            if (newValue != null) {
                btnSubscribir.setDisable(false);
                //pruebas peligrosas
            }
        }
    }

    @FXML
    private void subscribirse(ActionEvent event) {
        User user = new User();
        user.setEmail("admin@gmail.com");
        String host = "localhost";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getInstance(properties, new Authenticator() {
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tartangagrupo3c@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setHeader("Hi", user.getLogin());
            message.setText("Hi, This mail is to remind you that u have subscribed to the event " + e.getDescripcion());
        } catch (MessagingException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modificar(ActionEvent event) {
        User user = new User();
        user.setPrivilege(ADMIN);
        try {
            if (user.getPrivilege() == user.getPrivilege().ADMIN) {
                char filtroString = tfTipoEvento.getText().charAt(0);
                String filtroNumerico = tfIdLugar.getText();
                if (filtroNumerico.matches("-?([0-9]*)?") && ((filtroString >= 'a' && filtroString <= 'z') || (filtroString >= 'A' && filtroString <= 'Z') || filtroString == ' ')) {

                    e.setFecha(Date.from(dpFecha.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                    e.setLugar(tvTablaEvento.getSelectionModel().getSelectedItem().getLugar());
                    e.setIdEvento(tvTablaEvento.getSelectionModel().getSelectedItem().getIdEvento());
                    e.setDescripcion(tfDescripcion.getText());
                    e.setPremio(tfPremio.getText());
                    e.setTipoEvento(tfTipoEvento.getText());

                    emf.getFactory().modifyEvent_XML(e);
                    eventsData = FXCollections.observableArrayList(cargarDatos());
                } else {
                    Alert ventanita = new Alert(Alert.AlertType.ERROR);
                    ventanita.setHeaderText(null);
                    ventanita.setTitle("Error");
                    ventanita.setContentText("Caracteres incorrectos");
                    Optional<ButtonType> action = ventanita.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        tfDescripcion.setText("");
                        tfIdLugar.setText("");
                        tfPremio.setText("");
                        tfTipoEvento.setText("");
                        ventanita.close();
                    }
                }
            }
        } catch (WebApplicationException | ReadException e) {
            e.getMessage();
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        Alert ventanaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        ventanaConfirmacion.setHeaderText(null);
        ventanaConfirmacion.setTitle("Advertencia");
        ventanaConfirmacion.setContentText("¿Deseas salir?");
        Optional<ButtonType> accion = ventanaConfirmacion.showAndWait();

        if (accion.get() == ButtonType.OK) {
            Platform.exit();
        } else {
            ventanaConfirmacion.close();
        }
    }

    private void crear(ActionEvent event) {
        User user = new User();
        user.setPrivilege(ADMIN);

        try {
            if (user.getPrivilege() == user.getPrivilege().ADMIN) {
                char filtroString = tfTipoEvento.getText().charAt(0);
                String filtroNumerico = tfIdLugar.getText();
                if (filtroNumerico.matches("-?([0-9]*)?") && ((filtroString >= 'a' && filtroString <= 'z') || (filtroString >= 'A' && filtroString <= 'Z'))) {
                    Lugar l = new Lugar();
                    int lug = Integer.parseInt(tfIdLugar.getText());
                    l.setIdLugar(lug);

                    e.setDescripcion(tfDescripcion.getText());
                    e.setFecha(Date.from(dpFecha.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                    e.setLugar(l);
                    e.setPremio(tfPremio.getText());
                    e.setTipoEvento(tfTipoEvento.getText());

                    emf.getFactory().createEvent_XML(e);
                    eventsData = FXCollections.observableArrayList(cargarDatos());
                    tfDescripcion.setText("");
                    tfIdLugar.setText("");
                    tfPremio.setText("");
                    tfTipoEvento.setText("");
                } else {
                    Alert ventanita = new Alert(Alert.AlertType.ERROR);
                    ventanita.setHeaderText(null);
                    ventanita.setTitle("Error");
                    ventanita.setContentText("Caracteres incorrectos");
                    Optional<ButtonType> action = ventanita.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        tfDescripcion.setText("");
                        tfIdLugar.setText("");
                        tfPremio.setText("");
                        tfTipoEvento.setText("");
                        ventanita.close();
                    }
                }
            }
        } catch (WebApplicationException e) {
            e.getMessage();
        } catch (CreateException e) {
            e.getMessage();
        } catch (ReadException e) {
            e.getMessage();
        }
    }

    private void borrar(ActionEvent event) {
        User user = new User();
        user.setPrivilege(ADMIN);
        try {
            if (user.getPrivilege() == user.getPrivilege().ADMIN) {
                Evento es = tvTablaEvento.getSelectionModel().getSelectedItem();
                emf.getFactory().deleteEvent(es.getIdEvento().toString());
                eventsData = FXCollections.observableArrayList(cargarDatos());
                tfDescripcion.setText("");
                tfIdLugar.setText("");
                tfPremio.setText("");
                tfTipoEvento.setText("");
            }
        } catch (WebApplicationException | DeleteException | ReadException e) {
            e.getMessage();
        }
    }

    @FXML
    private ObservableList<Evento> cargarDatos() throws WebApplicationException, ReadException {
        ObservableList<Evento> listEvento;
        List<Evento> todosEventos;

        todosEventos = emf.getFactory().viewEvents_XML(new GenericType<List<Evento>>() {
        });
        listEvento = FXCollections.observableArrayList(todosEventos);
        tvTablaEvento.setItems(listEvento);
        tvTablaEvento.refresh();
        return listEvento;
    }

    private void informe(ActionEvent event) {
        try {
            JasperReport jr = JasperCompileManager.compileReport(getClass().getResourceAsStream("/grupo3/reto2/report/Event_report.jrxml"));
            JRBeanCollectionDataSource dataItems;
            dataItems = new JRBeanCollectionDataSource((Collection<Evento>) this.tvTablaEvento.getItems());
            Map<String, Object> params = new HashMap<>();
            JasperPrint jp = JasperFillManager.fillReport(jr, params, dataItems);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void ayuda(ActionEvent event) {
        try {
            Stage mainStage = new Stage();
            URL viewLink = getClass().getResource("/grupo3/reto2/view/HelpEvent.fxml");
            // initialition loader
            FXMLLoader loader = new FXMLLoader(viewLink);
            //make the root with the loader
            Parent root = (Parent) loader.load();
            //Get the controller
            HelpEventController mainStageController = ((HelpEventController) loader.getController());
            //set the stage
            mainStageController.setStage(mainStage);
            //start the stage
            mainStageController.initStage(root);
            this.stage.close();
        } catch (IOException ex) {
            Logger.getLogger(PlaceController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private void buscar(ActionEvent event) {
        Object newValue = new Object();
        if (tfFiltro.getText().length() > 50) {
            Alert ventanita = new Alert(Alert.AlertType.ERROR);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Error");
            ventanita.setContentText("Demasiados carácteres");
            Optional<ButtonType> action = ventanita.showAndWait();
            if (action.get() == ButtonType.OK) {
                tfDescripcion.setText("");
                tfIdLugar.setText("");
                tfPremio.setText("");
                tfTipoEvento.setText("");
                ventanita.close();
            }
        } else if (tfFiltro.getText().isEmpty()) {
            Alert ventanita = new Alert(Alert.AlertType.ERROR);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Error");
            ventanita.setContentText("Campos vacios");
            Optional<ButtonType> action = ventanita.showAndWait();
            if (action.get() == ButtonType.OK) {
                tfFiltro.setText("");
                ventanita.close();
            }
        } else {
            switch (cbFiltro.getValue().toString()) {
                case ("Filtrar por Id de evento"):
                    try {
                        String filtroNumerico = tfFiltro.getText();
                        if (filtroNumerico.matches("-?([0-9]*)?")) {
                            ObservableList<Evento> listEvento;
                            List<Evento> todosEventos;
                            todosEventos = emf.getFactory().findEventByEventId_XML(new GenericType<List<Evento>>() {
                            }, tfFiltro.getText());
                            listEvento = FXCollections.observableArrayList(todosEventos);
                            tvTablaEvento.setItems(listEvento);
                            tvTablaEvento.refresh();
                        } else {
                            tfFiltro.setText("");
                            Alert ventanita = new Alert(Alert.AlertType.ERROR);
                            ventanita.setHeaderText(null);
                            ventanita.setTitle("Error");
                            ventanita.setContentText("Caracteres incorrectos");
                            Optional<ButtonType> action = ventanita.showAndWait();
                            if (action.get() == ButtonType.OK) {
                                tfDescripcion.setText("");
                                tfIdLugar.setText("");
                                tfPremio.setText("");
                                tfTipoEvento.setText("");
                                ventanita.close();
                            }
                        }
                    } catch (ClientErrorException e) {
                        e.getMessage();
                    } catch (ReadException e) {
                        e.getMessage();
                    }
                    break;
                case ("Filtrar por tipo de evento"):
                    try {
                        char filtroString = tfFiltro.getText().charAt(0);
                        // Si no está entre a y z, ni entre A y Z, ni es un espacio
                        if (((filtroString >= 'a' && filtroString <= 'z') || (filtroString >= 'A' && filtroString <= 'Z') || filtroString == ' ')) {
                            try {
                                ObservableList<Evento> listEvento;
                                List<Evento> todosEventos;

                                todosEventos = emf.getFactory().findEventByType_XML(new GenericType<List<Evento>>() {
                                }, tfFiltro.getText());
                                listEvento = FXCollections.observableArrayList(todosEventos);
                                tvTablaEvento.setItems(listEvento);
                                tvTablaEvento.refresh();
                            } catch (ClientErrorException e) {
                                e.getMessage();
                            } catch (ReadException ex) {
                                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            tfFiltro.setText("");
                            Alert ventanita = new Alert(Alert.AlertType.ERROR);
                            ventanita.setHeaderText(null);
                            ventanita.setTitle("Error");
                            ventanita.setContentText("Caracteres incorrectos");
                            Optional<ButtonType> action = ventanita.showAndWait();
                            if (action.get() == ButtonType.OK) {
                                tfDescripcion.setText("");
                                tfIdLugar.setText("");
                                tfPremio.setText("");
                                tfTipoEvento.setText("");
                                ventanita.close();
                            }
                        }
                    } catch (ClientErrorException e) {
                        e.getMessage();
                    }
                    break;
            }
        }
    }
}