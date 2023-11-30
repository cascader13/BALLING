import javax.swing.*;
import java.awt.*;

class CircleDrawer extends JPanel {
    private int currentX = 0;
    private int currentY = 0;
    public void paintComponent(Graphics g) {
    g.setColor(new Color(100, 100, 100));
    g.fillOval(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
    }
    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            this.updateUI();
        }
    }
}
/*#include <gtkmm.h>

using namespace std;

class Window : public Gtk::Window {
    int x, y;
    int x1 = 1, y1 = 1;

    int w = 300, h = 300;
    int flag = 1;
public:
    Window() {
        set_size_request(w, h);
        x = 0;
        y = 110;
        Glib::signal_timeout().connect(sigc::mem_fun(*this, &Window::on_time), 10);
    }
    bool on_draw(const Cairo::RefPtr<Cairo::Context> &cr) {
        cr->rectangle(0, 0, get_width(), get_height());
        cr->fill();
        draw_ball(cr);
        cr->clip();
        return true;
    }

    void draw_ball(const Cairo::RefPtr<Cairo::Context> &cr) {
        cr->save();
        cr->set_source_rgb(1, 0, 0);
        cr->arc(x, y, 5, 0.0, 2 * M_PI);
        cr->fill();
        cr->restore();
    }

    bool on_time() {

        x += x1;
        y += y1;

        if(x >= get_width()) x1 = -1;
        if(x <= 0) x1 = 1;
        if(y >= get_height()) y1 = -1;
        if(y <= 0) y1 = 1;

        if(x == w && y == h) flag = 0;
        else if(x == 0 && y == 0) flag = 1;

        queue_draw();
        return true;
    }
};

int main(int argc, char ** argv)
{
    auto app = Gtk::Application::create(argc, argv);
    Window wnd;
    return app->run(wnd);
}
*/