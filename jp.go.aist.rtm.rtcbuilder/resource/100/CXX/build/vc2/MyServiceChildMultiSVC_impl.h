// -*-C++-*-
/*!
 * @file  MyServiceChildMultiSVC_impl.h
 * @brief Service implementation header of MyServiceChildMulti.idl
 *
 */

#include "MyServiceParent1Skel.h"
#include "MyServiceParent2Skel.h"

#include "MyServiceChildMultiSkel.h"

#ifndef MYSERVICECHILDMULTISVC_IMPL_H
#define MYSERVICECHILDMULTISVC_IMPL_H
 
/*!
 * @class MyServiceSVC_impl
 * Example class implementing IDL interface MyService
 */
class MyServiceSVC_impl
 : public virtual POA_MyService,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~MyServiceSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   MyServiceSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~MyServiceSVC_impl();

   // attributes and operations
   EchoList1* get_echo_history();
   ValueList1* get_value_history();

};

/*!
 * @class MyService2SVC_impl
 * Example class implementing IDL interface MyService2
 */
class MyService2SVC_impl
 : public virtual POA_MyService2,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~MyService2SVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   MyService2SVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~MyService2SVC_impl();

   // attributes and operations
   void setGain(::CORBA::Float gain);
   ::CORBA::Float getGain();

};

/*!
 * @class MyServiceChildSVC_impl
 * Example class implementing IDL interface MyServiceChild
 */
class MyServiceChildSVC_impl
 : public virtual POA_MyServiceChild,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~MyServiceChildSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   MyServiceChildSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~MyServiceChildSVC_impl();

   // attributes and operations
   void setPos(::CORBA::Float pos);
   EchoList1* getPos();
   EchoList1* get_echo_history();
   ValueList1* get_value_history();
   void setGain(::CORBA::Float gain);
   ::CORBA::Float getGain();

};



#endif // MYSERVICECHILDMULTISVC_IMPL_H


