package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.service.ICustomerService;
import com.javaweb.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService implements ICustomerService {

    CustomerRepository customerRepository;
    CustomerRepositoryCustom customerRepositoryCustom;
    CustomerConverter customerConverter;
    UserRepository userRepository;

    @Override
    public List<CustomerEntity> findAll(CustomerSearchRequest customerSearchRequest) {
        return customerRepositoryCustom.findAll(customerSearchRequest);
    }

    @Override
    public int countTotalItem(CustomerSearchRequest customerSearchRequest) {
        return customerRepositoryCustom.countTotalItem(customerSearchRequest);
    }

    @Override
    public CustomerEntity findById(Long id) {
        return customerRepository.findById(id);
    }

    @Transactional
    @Override
    public boolean addOrUpdateCustomer(CustomerDTO customerDTO) {
        if (validateCreateOrUpdateCustomer(customerDTO)) {
            CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
            customerRepository.save(customerEntity);
            return true;
        }
        return false;
    }

    private boolean validateCreateOrUpdateCustomer(CustomerDTO customerDTO) {
        if (!StringUtils.check(customerDTO.getPhone())) return false;
        if (!StringUtils.check(customerDTO.getFullName())) return false;
        if (!StringUtils.check(customerDTO.getStatus())) return false;
        return true;
    }

    @Override
    public boolean deleteCustomers(List<Long> ids) {
        if (!ids.isEmpty()) {
            int count = customerRepository.countByIdIn(ids);

            if (count != ids.size()) {
                throw new MyException("Customer not found!");
            } else {
                for (Long id : ids) {
                    CustomerEntity customer = customerRepository.findById(id);
                    customer.setIsActive((byte) 0);
                    customerRepository.save(customer);
                }
                return true;
            }
        }
        return false;
    }

    @Transactional
    @Override
    public void updateAssignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO) {
        CustomerEntity customer = customerRepository.findById(assignmentCustomerDTO.getCustomerId());

        List<UserEntity> staffs = userRepository.findAllById(assignmentCustomerDTO.getStaffs());
        customer.setUsers(staffs);

        customerRepository.save(customer);
    }
}
