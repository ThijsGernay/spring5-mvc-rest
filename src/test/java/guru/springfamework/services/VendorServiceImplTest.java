package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class VendorServiceImplTest {

    private static final String NAME = "vendor1";
    private static final Long ID = 1L;

    @Mock
    VendorRepository vendorRepository;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository, vendorMapper);
    }

    @Test
    public void getAllVendors() {

        Vendor vendor1 = new Vendor();
        vendor1.setName("vendor1");
        vendor1.setId(1L);

        Vendor vendor2 = new Vendor();
        vendor1.setName("vendor2");
        vendor1.setId(2L);

        when(vendorRepository.findAll()).thenReturn(Arrays.asList(vendor1, vendor2));

        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();
        assertEquals(2, vendorDTOS.size());

    }

    @Test
    public void getVendorById() {

        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(ID);
        assertEquals(NAME, vendorDTO.getName());

    }

    @Test
    public void createNewVendor() {

        VendorDTO vendor = new VendorDTO();
        vendor.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setId(ID);
        savedVendor.setName(NAME);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO vendorDTO = vendorService.createNewVendor(vendor);

        assertEquals(vendor.getName(), vendorDTO.getName());

    }

    @Test
    public void saveVendorByDTO() {

        VendorDTO vendor = new VendorDTO();
        vendor.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setId(ID);
        savedVendor.setName(NAME);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO vendorDTO = vendorService.saveVendorByDTO(ID, vendor);

        assertEquals(vendor.getName(), vendorDTO.getName());

    }

    @Test
    public void deleteVendorById() {

        vendorService.deleteVendorById(ID);

        verify(vendorRepository, times(1)).deleteById(anyLong());

    }
}