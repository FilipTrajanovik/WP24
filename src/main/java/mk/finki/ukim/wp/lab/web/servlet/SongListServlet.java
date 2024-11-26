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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="SongServlet", urlPatterns = "/listSongs")

public class SongListServlet extends HttpServlet {
    private final SongService songService;
    private final SpringTemplateEngine templateEngine;

    public SongListServlet(SongService songService, SpringTemplateEngine templateEngine) {
        this.songService = songService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("songs", songService.listSongs());
        templateEngine.process("listSongs.html", context, resp.getWriter());

        //logic for finding a song by title
        String title=req.getParameter("search");
        List<Song> songsFound=new ArrayList<Song>();
        if(title!=null && !title.trim().isEmpty()){
            songsFound=songService.searchSongsByTitle(title);
        }
        context.setVariable("songsFound", songsFound);
        templateEngine.process("listSongs.html", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId = req.getParameter("trackId");
        req.getSession().setAttribute("selectedTrackId", trackId);


        resp.sendRedirect(req.getContextPath() + "/artist");
    }

}