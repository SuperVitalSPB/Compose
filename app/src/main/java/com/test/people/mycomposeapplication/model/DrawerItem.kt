package com.test.people.mycomposeapplication.model

sealed class DrawerItem(val caption: String)

class DrawerItemHome(caption: String): DrawerItem(caption)
class DrawerItemContact(caption: String): DrawerItem(caption)
class DrawerItemAbout(caption: String): DrawerItem(caption)
