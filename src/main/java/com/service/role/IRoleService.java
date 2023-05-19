package com.service.role;

import com.model.Role;
import com.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}
