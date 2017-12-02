// -*-C++-*-
/*!
 * @file  MyServiceModuleTypeDefSVC_impl.h
 * @brief Service implementation header of MyServiceModuleTypeDef.idl
 *
 */

#include "MyServiceModuleTypeDefSkel.h"

#ifndef MYSERVICEMODULETYPEDEFSVC_IMPL_H
#define MYSERVICEMODULETYPEDEFSVC_IMPL_H
 
/*!
 * @class RTC_RTM_MyServiceSVC_impl
 * Example class implementing IDL interface RTC::RTM::MyService
 */
class RTC_RTM_MyServiceSVC_impl
 : public virtual POA_RTC::RTM::MyService,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~RTC_RTM_MyServiceSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   RTC_RTM_MyServiceSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~RTC_RTM_MyServiceSVC_impl();

   // attributes and operations
   RTC::RTM::Time echo(const RTC::RTM::Time& msg, const char* str);

};



#endif // MYSERVICEMODULETYPEDEFSVC_IMPL_H


