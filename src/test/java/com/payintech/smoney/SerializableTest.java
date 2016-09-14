/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 - 2016 PayinTech
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.payintech.smoney;

import com.payintech.smoney.entity.AddressEntity;
import com.payintech.smoney.entity.CompanyEntity;
import com.payintech.smoney.entity.UserEntity;
import com.payintech.smoney.enumeration.CountryCodeEnum;
import junit.framework.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;

/**
 * SerializableTest.
 *
 * @author Thibault Meyer
 * @version 16.02.01
 * @since 16.02.01
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SerializableTest {

    @Test
    public void serial_001() throws IOException, ClassNotFoundException {
        final AddressEntity addressEntity = new AddressEntity();
        addressEntity.City = "City";
        addressEntity.Street = "Street";
        addressEntity.ZipCode = "ZipCode";
        addressEntity.Country = CountryCodeEnum.FR;

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(addressEntity);

        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(bais);
        final AddressEntity addressEntity2 = (AddressEntity) ois.readObject();

        baos.close();
        bais.close();
        ois.close();
        oos.close();

        Assert.assertEquals(addressEntity.City, addressEntity2.City);
        Assert.assertEquals(addressEntity.Street, addressEntity2.Street);
        Assert.assertEquals(addressEntity.ZipCode, addressEntity2.ZipCode);
        Assert.assertEquals(addressEntity.Country, addressEntity2.Country);
    }

    @Test
    public void serial_002() throws IOException, ClassNotFoundException {
        final UserEntity userEntity = new UserEntity();
        final CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.Name = "My Company";
        companyEntity.Siret = "0000000";
        userEntity.AppUserId = "ACC-000000001";
        userEntity.Company = companyEntity;

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(userEntity);

        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(bais);
        final UserEntity userEntity2 = (UserEntity) ois.readObject();

        baos.close();
        bais.close();
        ois.close();
        oos.close();

        Assert.assertEquals(userEntity.AppUserId, userEntity2.AppUserId);
        Assert.assertEquals(userEntity.Company.Name, userEntity2.Company.Name);
        Assert.assertEquals(userEntity.Company.Siret, userEntity2.Company.Siret);
    }
}
