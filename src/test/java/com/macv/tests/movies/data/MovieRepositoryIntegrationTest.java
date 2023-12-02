package com.macv.tests.movies.data;

import com.macv.tests.movies.model.Genre;
import com.macv.tests.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

public class MovieRepositoryIntegrationTest {

    private MovieRepositoryJdbc movieRepository;
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSource = new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        movieRepository = new MovieRepositoryJdbc(jdbcTemplate);
    }

    @Test
    public void loadAllMovies() throws SQLException {

        Collection<Movie> movies = movieRepository.findAll();

        MatcherAssert.assertThat(movies, CoreMatchers.is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        )));
    }

    @Test
    public void loadMovieById(){

        Movie movie = movieRepository.findById(2);
        MatcherAssert.assertThat(movie, CoreMatchers.is(new Movie(2, "Memento", 113, Genre.THRILLER)));
    }

    @Test
    public void insertMovie() {
        Movie movie = new Movie(4, "Super 8", 112, Genre.THRILLER);
        movieRepository.saveOrUpdate(movie);

        Movie newMovie = movieRepository.findById(4);

        MatcherAssert.assertThat(newMovie, CoreMatchers.is(movie));
    }

    @After
    public void tearDown() throws Exception {

        final Statement s = dataSource.getConnection().createStatement();

        s.execute("drop all objects delete files");
    }

}