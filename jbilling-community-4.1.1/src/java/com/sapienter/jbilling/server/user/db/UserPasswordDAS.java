/*
jBilling - The Enterprise Open Source Billing System
Copyright (C) 2003-2011 Enterprise jBilling Software Ltd. and Emiliano Conde

This file is part of jbilling.

jbilling is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

jbilling is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with jbilling.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.sapienter.jbilling.server.user.db;

import com.sapienter.jbilling.common.FormatLogger;
import com.sapienter.jbilling.server.util.db.AbstractDAS;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class UserPasswordDAS extends AbstractDAS<UserPasswordDTO> {
    private static final FormatLogger LOG = new FormatLogger(Logger.getLogger(UserDAS.class));

    public List<String> findLastSixPasswords(UserDTO userDTO,String newPassword){
        Criteria criteria = getSession().createCriteria(UserPasswordDTO.class);
        criteria.add(Restrictions.eq("user",userDTO))
        .addOrder(Order.desc("dateCreated"))
        .setMaxResults(6)
        .setProjection(Projections.property("encryptedPassword"));
        return !criteria.list().isEmpty()?(List<String>) criteria.list():new ArrayList<String>();
    }
}
