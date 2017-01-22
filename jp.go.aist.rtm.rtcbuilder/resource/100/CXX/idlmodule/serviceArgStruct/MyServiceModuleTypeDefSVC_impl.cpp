// -*-C++-*-
/*!
 * @file  MyServiceModuleTypeDefSVC_impl.cpp
 * @brief Service implementation code of MyServiceModuleTypeDef.idl
 *
 */

#include "MyServiceModuleTypeDefSVC_impl.h"

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
Time MyServiceSVC_impl::echo(Time& msg, const char* str)
{
	Time result;
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <Time MyServiceSVC_impl::echo(Time& msg, const char* str)>"
#endif
  return result;
}



// End of example implementational code



