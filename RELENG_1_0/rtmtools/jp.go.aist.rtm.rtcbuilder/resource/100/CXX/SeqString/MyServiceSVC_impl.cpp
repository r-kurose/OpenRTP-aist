// -*-C++-*-
/*!
 * @file  MyServiceSVC_impl.cpp
 * @brief Service implementation code of MyService.idl
 *
 */

#include "MyServiceSVC_impl.h"

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
char* MyServiceSVC_impl::echo(const CORBA::WChar* msg)
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <char* MyServiceSVC_impl::echo(const CORBA::WChar* msg)>"
#endif
  return 0;
}



// End of example implementational code



