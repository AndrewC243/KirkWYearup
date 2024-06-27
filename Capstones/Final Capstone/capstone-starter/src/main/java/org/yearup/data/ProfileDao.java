package org.yearup.data;


import org.yearup.models.Profile;

public interface ProfileDao
{
    Profile getByUserId(int id);
    Profile create(Profile profile);
    Profile update(int id, Profile profile);
}
