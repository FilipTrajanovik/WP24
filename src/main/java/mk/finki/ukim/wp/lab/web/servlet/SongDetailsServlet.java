package mk.finki.ukim.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "SongDetailsServlet", urlPatterns = "/songDetails")
public class SongDetailsServlet extends HttpServlet {
    private final SongService songService;
    private final SpringTemplateEngine templateEngine;

    public SongDetailsServlet(SongService songService, SpringTemplateEngine templateEngine) {
        this.songService = songService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        // Retrieve the track ID from the session
        String trackId = (String) req.getSession().getAttribute("trackId");
        if (trackId == null) {
            resp.sendRedirect(req.getContextPath() + "/listSongs"); // Redirect if no trackId is found
            return;
        }

        // Fetch the song and its performers using the track ID
        Song song = songService.findByTrackId(trackId);
        if (song == null) {
            resp.sendRedirect(req.getContextPath() + "/listSongs"); // Redirect if song is not found
            return;
        }

        // Prepare the context with song details and performers
        WebContext context = new WebContext(webExchange);
        context.setVariable("songTitle", song.getTitle());
        context.setVariable("songGenre", song.getGenre());
        context.setVariable("releaseYear", song.getReleaseYear());
        context.setVariable("performers", song.getPerformers()); // Assuming song.getPerformers() returns a List<Artist>

        // Render the song details template
        templateEngine.process("songDetails.html", context, resp.getWriter());
    }
}

