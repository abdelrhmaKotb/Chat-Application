package gov.iti.jets.business.mapper;

public interface Mapper<E,R> {
    R getContactDto(E entity);
}
