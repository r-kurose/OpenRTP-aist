// -*-C++-*-
/*!
 * @file  MyServiceChildWithTypeSVC_impl.h
 * @brief Service implementation header of MyServiceChildWithType.idl
 *
 */

#include "MyServiceParentWithTypeSkel.h"

#include "MyServiceChildWithTypeSkel.h"

#ifndef MYSERVICECHILDWITHTYPESVC_IMPL_H
#define MYSERVICECHILDWITHTYPESVC_IMPL_H
 
/*!
 * @class MyServiceWithTypeSVC_impl
 * Example class implementing IDL interface MyServiceWithType
 */
class MyServiceWithTypeSVC_impl
 : public virtual POA_MyServiceWithType,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~MyServiceWithTypeSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   MyServiceWithTypeSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~MyServiceWithTypeSVC_impl();

   // attributes and operations
   EchoList* get_echo_history();
   ValueList* get_value_history();

};

/*!
 * @class MyServiceWithTypeChildSVC_impl
 * Example class implementing IDL interface MyServiceWithTypeChild
 */
class MyServiceWithTypeChildSVC_impl
 : public virtual POA_MyServiceWithTypeChild,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~MyServiceWithTypeChildSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   MyServiceWithTypeChildSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~MyServiceWithTypeChildSVC_impl();

   // attributes and operations
   void setPos(::CORBA::Float pos);
   EchoList* getPos();
   EchoList* get_echo_history();
   ValueList* get_value_history();

};



#endif // MYSERVICECHILDWITHTYPESVC_IMPL_H


