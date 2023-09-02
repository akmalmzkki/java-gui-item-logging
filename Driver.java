import control.Control;
import view.View;
import model.Model;

public class Driver {
    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        Control control = new Control(view, model);

        view.setVisible(true);
    }
}
