package aceofspades.frames;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawOptionsMouse extends MouseAdapter {

    DrawOptions _draw;
    Frame _frame;

    DrawOptionsMouse(Frame f, DrawStrategy draw) {
        _draw = (DrawOptions) draw;
        _frame = f;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        /*
         * OnHover Main Menu Button
         */
        if (mx > _draw.menuButton.x && mx < (_draw.menuButton.x + _draw.menuButton.width)
                && my > _draw.menuButton.y && my < (_draw.menuButton.y + _draw.menuButton.height)) {
            _draw.hoverMenuButton = true;
        } else {
            _draw.hoverMenuButton = false;
        }
        /*
         * OnHover Change Resoluion Button
         */
        if (mx > _draw.resolutionButton.x && mx < (_draw.resolutionButton.x + _draw.resolutionButton.width)
                && my > _draw.resolutionButton.y && my < (_draw.resolutionButton.y + _draw.resolutionButton.height)) {
            _draw.hoverResolutionButton = true;
        } else {
            _draw.hoverResolutionButton = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        /*
         * OnClick Main Menu Button
         */
        if (mx > _draw.menuButton.x && mx < (_draw.menuButton.x + _draw.menuButton.width)
                && my > _draw.menuButton.y && my < (_draw.menuButton.y + _draw.menuButton.height)) {
            if (_draw.changedResolution) {
                _frame._width = _draw.res_width;
                _frame._height = _draw.res_height;
                _frame.setBounds((_frame.screenSize.width / 2) - (_frame._width / 2), (_frame.screenSize.height / 2) - (_frame._height / 2), _frame._width, _frame._height);
            }
            DrawStrategy draw = new DrawMenu(_frame);
            _frame.setDrawStrategy(draw);
            _frame.setMouseListener(new DrawMenuMouse(_frame, draw));
            _frame.setMouseMotionListener(new DrawMenuMouse(_frame, draw));
        }
        /*
         * OnClick Change Resolution Button
         */
        if (mx > _draw.resolutionButton.x && mx < (_draw.resolutionButton.x + _draw.resolutionButton.width)
                && my > _draw.resolutionButton.y && my < (_draw.resolutionButton.y + _draw.resolutionButton.height)) {
            _draw.changeResolution();
        }
    }
}
