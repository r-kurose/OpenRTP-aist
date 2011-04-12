// -*-C++-*-
/*!
 * @file  MyServiceChildMultiSVC_impl.cpp
 * @brief Service implementation code of MyServiceChildMulti.idl
 *
 */

#include "MyServiceChildMultiSVC_impl.h"

/*
 * Example implementational code for IDL interface MyService
 */
MyServiceSVC_impl::MyServiceSVC_impl()
{
  // Please add extra constructor code here.
}


MyServiceSVC_impl::~MyServiceSVC_impl()
{
  // Please add extra destructor code here.
}


/*
 * Methods corresponding to IDL attributes and operations
 */
EchoList1* MyServiceSVC_impl::get_echo_history()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <EchoList1* MyServiceSVC_impl::get_echo_history()>"
#endif
  return 0;
}

ValueList1* MyServiceSVC_impl::get_value_history()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <ValueList1* MyServiceSVC_impl::get_value_history()>"
#endif
  return 0;
}



// End of example implementational code

/*
 * Example implementational code for IDL interface MyService2
 */
MyService2SVC_impl::MyService2SVC_impl()
{
  // Please add extra constructor code here.
}


MyService2SVC_impl::~MyService2SVC_impl()
{
  // Please add extra destructor code here.
}


/*
 * Methods corresponding to IDL attributes and operations
 */
void MyService2SVC_impl::setGain(CORBA::Float gain)
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <void MyService2SVC_impl::setGain(CORBA::Float gain)>"
#endif
}

CORBA::Float MyService2SVC_impl::getGain()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <CORBA::Float MyService2SVC_impl::getGain()>"
#endif
  return 0;
}



// End of example implementational code

/*
 * Example implementational code for IDL interface MyServiceChild
 */
MyServiceChildSVC_impl::MyServiceChildSVC_impl()
{
  // Please add extra constructor code here.
}


MyServiceChildSVC_impl::~MyServiceChildSVC_impl()
{
  // Please add extra destructor code here.
}


/*
 * Methods corresponding to IDL attributes and operations
 */
void MyServiceChildSVC_impl::setPos(CORBA::Float pos)
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <void MyServiceChildSVC_impl::setPos(CORBA::Float pos)>"
#endif
}

EchoList1* MyServiceChildSVC_impl::getPos()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <EchoList1* MyServiceChildSVC_impl::getPos()>"
#endif
  return 0;
}

EchoList1* MyServiceChildSVC_impl::get_echo_history()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <EchoList1* MyServiceChildSVC_impl::get_echo_history()>"
#endif
  return 0;
}

ValueList1* MyServiceChildSVC_impl::get_value_history()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <ValueList1* MyServiceChildSVC_impl::get_value_history()>"
#endif
  return 0;
}

void MyServiceChildSVC_impl::setGain(CORBA::Float gain)
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <void MyServiceChildSVC_impl::setGain(CORBA::Float gain)>"
#endif
}

CORBA::Float MyServiceChildSVC_impl::getGain()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <CORBA::Float MyServiceChildSVC_impl::getGain()>"
#endif
  return 0;
}



// End of example implementational code



