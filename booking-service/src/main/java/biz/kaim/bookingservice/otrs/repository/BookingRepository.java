package biz.kaim.bookingservice.otrs.repository;

import java.util.Collection;


public interface BookingRepository<Booking, String> extends Repository<Booking, String> {


    boolean containsName(String name);


    public Collection<Booking> findByName(String name) throws Exception;
}
