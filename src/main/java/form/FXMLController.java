package form;

import javafx.fxml.FXML;

public abstract class FXMLController {
    protected Form form;

    /**
     * Этот метод вызывается у контроллера после его создания и инициализации всех полей с аннотацией @FXML
     */
    @FXML
    public abstract void initialize();
}
