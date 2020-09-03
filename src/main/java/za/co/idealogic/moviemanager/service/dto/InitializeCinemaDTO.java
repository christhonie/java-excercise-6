package za.co.idealogic.moviemanager.service.dto;

import java.io.Serializable;

public class InitializeCinemaDTO implements Serializable
{
    private static final long serialVersionUID = -3906350449788888562L;

    private Long venueId;
    
    private String cinemaName;
    
    private int numberOfRows;
    
    private int longestRowCount;


    public Long getVenueId()
    {
        return venueId;
    }


    public void setVenueId(Long venueId)
    {
        this.venueId = venueId;
    }


    public String getCinemaName()
    {
        return cinemaName;
    }


    public void setCinemaName(String cinemaName)
    {
        this.cinemaName = cinemaName;
    }


    public int getNumberOfRows()
    {
        return numberOfRows;
    }


    public void setNumberOfRows(int numberOfRows)
    {
        this.numberOfRows = numberOfRows;
    }


    public int getLongestRowCount()
    {
        return longestRowCount;
    }


    public void setLongestRowCount(int longestRowCount)
    {
        this.longestRowCount = longestRowCount;
    }

}
