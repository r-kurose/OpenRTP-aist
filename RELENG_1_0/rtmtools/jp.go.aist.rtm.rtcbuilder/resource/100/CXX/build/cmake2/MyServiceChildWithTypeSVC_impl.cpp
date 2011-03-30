// -*-C++-*-
/*!
 * @file  MyServiceChildWithTypeSVC_impl.cpp
 * @brief Service implementation code of MyServiceChildWithType.idl
 *
 */

#include "MyServiceChildWithTypeSVC_impl.h"

/*
 * Example implementational code for IDL interface MyServiceWithType
 */
MyServiceWithTypeSVC_impl::MyServiceWithTypeSVC_impl()
{
  // Please add extra constructor code here.
}


MyServiceWithTypeSVC_impl::~MyServiceWithTypeSVC_impl()
{
  // Please add extra destructor code here.
}


/*
 * Methods corresponding to IDL attributes and operations
 */
EchoList* MyServiceWithTypeSVC_impl::get_echo_history()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <EchoList* MyServiceWithTypeSVC_impl::get_echo_history()>"
#endif
  return 0;
}

ValueList* MyServiceWithTypeSVC_impl::get_value_history()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <ValueList* MyServiceWithTypeSVC_impl::get_value_history()>"
#endif
  return 0;
}



// End of example implementational code

/*
 * Example implementational code for IDL interface MyServiceWithTypeChild
 */
MyServiceWithTypeChildSVC_impl::MyServiceWithTypeChildSVC_impl()
{
  // Please add extra constructor code here.
}


MyServiceWithTypeChildSVC_impl::~MyServiceWithTypeChildSVC_impl()
{
  // Please add extra destructor code here.
}


/*
 * Methods corresponding to IDL attributes and operations
 */
void MyServiceWithTypeChildSVC_impl::setPos(CORBA::Float pos)
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <void MyServiceWithTypeChildSVC_impl::setPos(CORBA::Float pos)>"
#endif
}

EchoList* MyServiceWithTypeChildSVC_impl::getPos()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <EchoList* MyServiceWithTypeChildSVC_impl::getPos()>"
#endif
  return 0;
}

EchoList* MyServiceWithTypeChildSVC_impl::get_echo_history()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <EchoList* MyServiceWithTypeChildSVC_impl::get_echo_history()>"
#endif
  return 0;
}

ValueList* MyServiceWithTypeChildSVC_impl::get_value_history()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <ValueList* MyServiceWithTypeChildSVC_impl::get_value_history()>"
#endif
  return 0;
}



// End of example implementational code



