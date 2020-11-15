package com.prodev.dms.config;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.prodev.dms.common.CityData;
import com.prodev.dms.domain.City;
import com.prodev.dms.domain.DeliProduct;
import com.prodev.dms.domain.Role;
import com.prodev.dms.domain.User;
import com.prodev.dms.domain.UserRole;
import com.prodev.dms.enums.RolesEnum;
import com.prodev.dms.repository.ICityRepository;
import com.prodev.dms.repository.IDeliProductRepository;
import com.prodev.dms.repository.IRoleRepository;
import com.prodev.dms.repository.IUserRepository;
import com.prodev.dms.services.UserService;

@Component
public class DeploymentListener implements ApplicationRunner,ApplicationListener<ContextRefreshedEvent> {
	Date today=	new Date();
	private static final Logger LOG = LoggerFactory.getLogger(DeploymentListener.class);
	
	//get the UserName from the application.properties file
	@Value("${security.default.user.admin.username}")
	private String userName;
	
	//get the password from the application.properties file
	@Value("${security.default.user.admin.password}")
	private String password;

	@Autowired
	UserService userService;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	IUserRepository userRepo;
	
	@Autowired
	ICityRepository cityRepo;
	
	@Autowired
	IDeliProductRepository productrepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			LOG.debug("Application deployment listener  is started.");
		
			/*
			 * Create MANAGER, DELIVERY, STAFF roles
			 */
			// Create MANAGER Role if not exist
			Role managerRole = new Role();
			managerRole.setId(RolesEnum.MANAGER.getId());
			managerRole.setName(RolesEnum.MANAGER.getRoleName());
			managerRole.setRoleLevel(1);
			managerRole.setActive(true);
			managerRole.setCreatedDate(today);
			managerRole.setCreatedUserID(1l);
			if (roleRepository.findByName(RolesEnum.MANAGER.getRoleName()) == null) {
				roleRepository.save(managerRole);
			}
			// Create DELIVERY Role if not exist
			Role deliveryRole = new Role();
			deliveryRole.setId(RolesEnum.DELIVERY.getId());
			deliveryRole.setName(RolesEnum.DELIVERY.getRoleName());
			deliveryRole.setRoleLevel(2);
			deliveryRole.setActive(true);
			deliveryRole.setCreatedDate(today);
			deliveryRole.setCreatedUserID(1l);
			if (roleRepository.findByName(RolesEnum.DELIVERY.getRoleName()) == null) {
				roleRepository.save(deliveryRole);
			}

			// Create STAFF Role if not exist
			Role staffRole = new Role();
			staffRole.setId(RolesEnum.STAFF.getId());
			staffRole.setName(RolesEnum.STAFF.getRoleName());
			staffRole.setRoleLevel(3);
			staffRole.setActive(true);
			staffRole.setCreatedDate(today);
			staffRole.setCreatedUserID(1l);
			if (roleRepository.findByName(RolesEnum.STAFF.getRoleName()) == null) {
				roleRepository.save(staffRole);
			}
			
			//create the super superAdmin for user login
			User superAdmin = new User();
			superAdmin.setUsername(userName);
			superAdmin.setPassword(password);
			superAdmin.setEnabled(true);
			superAdmin.setEmail("admin@gmail.com");
			superAdmin.setSecurityQuestion("hi");
			superAdmin.setSecurityAnswer("hi");
			superAdmin.setActive(true);
			superAdmin.setCreatedDate(today);
			superAdmin.setCreatedUserID(1l);
			if (userRepo.findByUsername(userName) == null) {
				Set<UserRole> userRoles = new HashSet<>();
				userRoles.add(new UserRole(superAdmin, managerRole));
				userRoles.add(new UserRole(superAdmin, deliveryRole));
				userRoles.add(new UserRole(superAdmin, staffRole));
				superAdmin.getUserRoles().addAll(userRoles);
				userService.createUser(superAdmin);
			}
			
			/*
			 * Create TownShip and city
			 */
//			LOG.debug("Creating township and city.");
//			// Create for Yangon city and its township
//			List<City> cityList =CityData.getCityList();//getting all City data 			
//			for (int i=0;i<cityList.size();i++) {
//				cityRepo.save(cityList.get(i) );
//			}
			LOG.debug("Application deployment listener is finised.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
//		DeliProduct p1 = new DeliProduct();
//        p1.setProductName("product1");      
//        p1.setProductCode("p001");
//        p1.setActive(true);
//        p1.setCreatedDate(today);
//        p1.setCreatedUserID(1l);
//        productrepo.save(p1);
//        LOG.info("Saved p1 - id: " + p1.getId());
	}

}
