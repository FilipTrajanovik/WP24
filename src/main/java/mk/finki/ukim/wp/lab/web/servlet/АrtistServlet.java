package mk.finki.ukim.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.ArtistService;
import mk.finki.ukim.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="ArtistServlet", urlPatterns = "/artist")
public class АrtistServlet extends HttpServlet {
    private final ArtistService artistService;
    private final SongService songService;
    private final SpringTemplateEngine templateEngine;

    public АrtistServlet(ArtistService artistService, SongService songService,SpringTemplateEngine templateEngine) {
        this.artistService = artistService;
        this.songService = songService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("artists", artistService.listArtists());
        context.setVariable("trackId", req.getSession().getAttribute("selectedTrackId"));
        templateEngine.process("artistsList.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId = (String) req.getSession().getAttribute("selectedTrackId");
        String artistId = req.getParameter("artistId");
        Artist artist=artistService.ArtistfindById(Long.parseLong(artistId));
        Song song=songService.findByTrackId(trackId);
        // Add the selected artist to the song's performers
        songService.addArtistToSong(artist, song);

        // Store the trackId in session for later retrieval in SongDetailsServlet
        req.getSession().setAttribute("trackId", trackId);

        // Redirect to the song details page
        resp.sendRedirect(req.getContextPath() + "/songDetails");
    }
}
