package gov.iti.jets.business.mapper;

public interface Mapper<E,R> {
    R toDto(E entity);

}
