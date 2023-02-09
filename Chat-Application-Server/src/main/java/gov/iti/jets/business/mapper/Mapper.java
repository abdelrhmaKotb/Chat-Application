package gov.iti.jets.business.mapper;

public interface Mapper<E,R> {
    //R getContactDto(E entity);
    R toDto(E e);
    E toEntity(R e);
}
