// -*-C++-*-
/*!
 * @file  MyServiceSVC_impl.h
 * @brief Service implementation header of MyService.idl
 *
 */

#include "MyServiceSkel.h"

#ifndef MYSERVICESVC_IMPL_H
#define MYSERVICESVC_IMPL_H
 
/*!
 * @class Test_MyServiceSVC_impl
 * Example class implementing IDL interface Test::MyService
 */
class Test_MyServiceSVC_impl
 : public virtual POA_Test::MyService,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~Test_MyServiceSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   Test_MyServiceSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~Test_MyServiceSVC_impl();

   // attributes and operations
   char* echo(const char* msg);
   Test::EchoList* get_echo_history();
   void set_value(::CORBA::Float value);
   ::CORBA::Float get_value();
   Test::ValueList* get_value_history();

};



#endif // MYSERVICESVC_IMPL_H


